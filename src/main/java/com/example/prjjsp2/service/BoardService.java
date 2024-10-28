package com.example.prjjsp2.service;

import com.example.prjjsp2.dto.Board;
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

    public void add(Board board) {
        mapper.insert(board);
    }

    public Map<String, Object> list(Integer page) {
        Integer offset = (page - 1) * 10;
        List<Board> list = mapper.selectAllPaging(offset);

        Map<String, Object> map = new HashMap<>();

        Integer countAll = mapper.countAll();
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

    public void remove(Integer id) {
        mapper.deleteById(id);
    }

    public void update(Board board) {
        mapper.update(board);
    }
}
