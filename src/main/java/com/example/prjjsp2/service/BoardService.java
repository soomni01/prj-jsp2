package com.example.prjjsp2.service;

import com.example.prjjsp2.dto.Board;
import com.example.prjjsp2.dto.Member;
import com.example.prjjsp2.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;

    public void add(Board board, Member member) {
        mapper.insert(board, member);
    }

    public Map<String, Object> list(Integer page, String searchTarget, String keyword) {
        Integer offset = (page - 1) * 10;
        List<Board> list = mapper.selectAllPaging(offset, searchTarget, keyword);

        Map<String, Object> map = new HashMap<>();

        Integer countAll = mapper.countAll(searchTarget, keyword);
        Integer lastPageNumber = (countAll - 1) / 10 + 1;
        Integer rightPageNumber = ((page - 1) / 10 + 1) * 10;
        Integer leftPageNumber = rightPageNumber - 9;
        Integer nextPageNumber = rightPageNumber + 1;
        Integer prevPageNumber = leftPageNumber - 1;

        Boolean hasNextPage = nextPageNumber < lastPageNumber;
        Boolean hasPrevPage = prevPageNumber > 0;

        rightPageNumber = Math.min(rightPageNumber, lastPageNumber);

        Map<String, Object> pageInfo = new HashMap<>();

        pageInfo.put("leftPageNumber", leftPageNumber);
        pageInfo.put("rightPageNumber", rightPageNumber);
        pageInfo.put("lastPageNumber", lastPageNumber);
        pageInfo.put("currentPageNumber", page);
        pageInfo.put("nextPageNumber", nextPageNumber);
        pageInfo.put("prevPageNumber", prevPageNumber);
        pageInfo.put("hasNextPage", hasNextPage);
        pageInfo.put("hasPrevPage", hasPrevPage);

        map.put("pageInfo", pageInfo);
        map.put("boardList", list);
        return map;
    }

    public Board get(Integer id) {
        Board board = mapper.selectById(id);
        return board;
    }

    public void remove(Integer id, Member member) {
        Board board = mapper.selectById(id);
        if (board.getWriter().equals(member.getId())) {
            mapper.deleteById(id);
        } else {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
    }

    public void update(Board board, Member member) {
        Board board1 = mapper.selectById(board.getId());
        if (board1.getWriter().equals(member.getId())) {
            mapper.update(board);
        } else {
            throw new RuntimeException("수정 권한이 없습니다.");
        }
    }
}
