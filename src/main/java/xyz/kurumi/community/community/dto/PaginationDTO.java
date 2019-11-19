package xyz.kurumi.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;//是否展示上一页

    private boolean showNext;//是否展示下一页
    private boolean showFirstPage;//是否展示第一页
    private boolean showEndPage;//是否展示末页
    private Integer Page;//当前页码
    private Set<Integer> pages = new TreeSet<>();//现实的页码集合

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        pages.add(page);
        for (int i = 0; i < 3; i++) {
            if (page - i > 0) {
                pages.add(page - i);
            }
        }
        for (int i = 0; i < 3; i++) {
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        this.Page = page;
        //是否展示上一页，如果当前页是第一页，没有上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否展示下一页，若当前页是末尾页，则不展示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
