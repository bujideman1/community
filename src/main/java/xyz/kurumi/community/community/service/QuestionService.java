package xyz.kurumi.community.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kurumi.community.community.dto.PaginationDTO;
import xyz.kurumi.community.community.dto.QuestionDTO;
import xyz.kurumi.community.community.mapper.QuestionMapper;
import xyz.kurumi.community.community.mapper.UserMapper;
import xyz.kurumi.community.community.model.Question;
import xyz.kurumi.community.community.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();//包含分页信息的问题集合
        Integer totalCount = questionMapper.count();
        Integer totalPage=null;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page > totalPage) {
            page = totalPage;
        } if (page < 1) {
            page = 1;
        }
        paginationDTO.setPagination(totalPage, page);


        Integer offset = page<1 ? 0: size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);//查询出每页的问题集合

        List<QuestionDTO> questionDTOS = new ArrayList<>();//包含问题集和用户信息的集合
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);

        return paginationDTO;
    }

    public PaginationDTO list(String userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();//包含分页信息的问题集合
        Integer totalCount = questionMapper.countByUserId(userId);

        Integer totalPage=null;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page > totalPage) {
            page = totalPage;
        } if (page < 1) {
            page = 1;
        }
        paginationDTO.setPagination(totalPage, page);

        Integer offset = page<1 ? 0: size * (page - 1);
        List<Question> questions = questionMapper.listByUser(userId,offset, size);//查询出每页的问题集合

        List<QuestionDTO> questionDTOS = new ArrayList<>();//包含问题集和用户信息的集合
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);

        return paginationDTO;
    }
}
