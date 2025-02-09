package com.ilkayburak.bitask.service.impl;

import com.ilkayburak.bitask.dto.BoardDTO;
import com.ilkayburak.bitask.dto.CreateBoardRequestDTO;
import com.ilkayburak.bitask.dto.UpdateBoardRequestDTO;
import com.ilkayburak.bitask.dto.core.ResponsePayload;
import com.ilkayburak.bitask.entity.Board;
import com.ilkayburak.bitask.entity.User;
import com.ilkayburak.bitask.enumarations.core.MessageEnum;
import com.ilkayburak.bitask.enumarations.core.ResponseEnum;
import com.ilkayburak.bitask.mapper.BoardDTOMapper;
import com.ilkayburak.bitask.repository.BoardRepository;
import com.ilkayburak.bitask.repository.UserRepository;
import com.ilkayburak.bitask.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardDTOMapper mapper;

    @Override
    @Transactional
    public ResponsePayload<BoardDTO> save(CreateBoardRequestDTO createBoardRequestDTO) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User creator = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        // Aynı isimde bir board olup olmadığını kontrol et
        Optional<Board> existingBoard = boardRepository.findByNameIgnoreCase(createBoardRequestDTO.getName().toLowerCase());
        if (existingBoard.isPresent()) {
            // Eğer aynı isimde bir board varsa hata mesajı döndür
            return new ResponsePayload<>(ResponseEnum.ERROR, MessageEnum.RECORD_EXISTS.getMessage());
        }
        // Eğer aynı isimde bir board yoksa board kaydını gerçekleştir
        Board board = mapper.convertToEntityForScreenDTO(createBoardRequestDTO, creator);
        return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.SAVE_SUCCESS.getMessage(), mapper.convertToDTO(boardRepository.save(board)));
    }


    @Override
    @Transactional
    public ResponsePayload<BoardDTO> update(UpdateBoardRequestDTO updateBoardRequestDTO) {
        Optional<Board> boardOptional = boardRepository.findById(updateBoardRequestDTO.getId());
        if (boardOptional.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
        }

        Board board = boardOptional.get();

        // Güncel kullanıcı doğrulama
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        if (userRepository.findByEmail(username).isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.UNAUTHORIZED, MessageEnum.NOT_AUTH.getMessage());
        }

        // Board adını güncelle
        Optional.ofNullable(updateBoardRequestDTO.getName()).ifPresent(board::setName);

        // Üyeleri ekle ve çıkar
        String validationError = validateAndProcessMembers(updateBoardRequestDTO, board);
        if (validationError != null) {
            return new ResponsePayload<>(ResponseEnum.NOTFOUND, validationError);
        }

        // Güncellenen board'u kaydet ve dön
        board = boardRepository.save(board);
        return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.UPDATE_SUCCESS.getMessage(),
                mapper.mapForUpdateBoardRequest(board, updateBoardRequestDTO));
    }




    @Override
    public ResponsePayload<BoardDTO> getById(Long id) {
        Optional<Board> board = boardRepository.findById(id);
      return board.map(value -> new ResponsePayload<>(ResponseEnum.OK, mapper.convertToDTO(value)))
          .orElseGet(() -> new ResponsePayload<>(ResponseEnum.NOTFOUND,
              MessageEnum.NOT_FOUND.getMessage()));
    }

    @Override
    public ResponsePayload<List<BoardDTO>> getAllBoardsForUser(Long id) {
        List<Board> boards = boardRepository.findAllByMembers_Id(id);
        if (!boards.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.mapList(boards));
        }
        return new ResponsePayload<>(ResponseEnum.BADREQUEST, MessageEnum.EMPTY_LIST.getMessage());
    }

    @Override
    public ResponsePayload<List<BoardDTO>> getAllBoardsByCreator(Long id) {
        List<Board> boards = boardRepository.findAllByCreator_Id(id);
        if (!boards.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.mapList(boards));
        }
        return new ResponsePayload<>(ResponseEnum.BADREQUEST, MessageEnum.EMPTY_LIST.getMessage());
    }

    @Override
    public ResponsePayload<List<BoardDTO>> getAllBoards() {
    List<Board> boards = boardRepository.findAll(Sort.by(Direction.DESC, "createDate"));
        if (!boards.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.OK, mapper.mapList(boards));
        }
        return new ResponsePayload<>(ResponseEnum.BADREQUEST, MessageEnum.EMPTY_LIST.getMessage());
    }

    @Override
    public ResponsePayload<Void> deleteById(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            boardRepository.deleteById(id);
            return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.DELETE_SUCCESS.getMessage());
        }
        return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
    }

    private String validateAndProcessMembers(UpdateBoardRequestDTO requestDTO, Board board) {
        // Üyeleri ekle
        List<User> membersToAdd = getUsersByIds(requestDTO.getMembersToAdd());
        if (membersToAdd == null) {
            return "Some members to add were not found in the database.";
        }
        board.getMembers().addAll(membersToAdd);

        // Üyeleri çıkar
        List<User> membersToRemove = getUsersByIds(requestDTO.getMembersToRemove());
        if (membersToRemove == null) {
            return "Some members to remove were not found in the database.";
        }
        board.getMembers().removeAll(membersToRemove);

        return null; // Hata yok
    }

    private List<User> getUsersByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of(); // Boş liste dön
        }
        List<User> users = userRepository.findAllById(ids);
        return users.size() == ids.size() ? users : null; // Eksik varsa null dön
    }
}
