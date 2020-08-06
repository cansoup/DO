package com.backend.service.impl;

import com.backend.dao.PostDao;
import com.backend.dto.post.Post;
import com.backend.dto.post.Tag;
import com.backend.service.PostService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Override
    public Long save(Post post) {

        postDao.save(post);
        Long pid = post.getPid();
        for(String tag : post.getTag()) {
            Long tid = postDao.findTagByName(tag);
            if(tid != null) { // 태그가 이미 존재하는 경우
                postDao.increaseTagHits(tag);
            } else {
                Tag newTag = new Tag(tag);
                postDao.saveNewTag(newTag);
                tid = newTag.getTid();
            } // 태그가 존재하지 않는 경우
            postDao.savePostTagList(pid, tid);
        }
        return 1L;
    }

    @Override
    @Transactional
    public Post findById(Long id) {
        postDao.increaseHits(id);           // 글 조회시 조회수 증가 Transaction 처리
        return postDao.findById(id);
    }

    @Override
    public List<Post> findByTitle(String title) {
        return postDao.findByTitle(title);
    }

    @Override
    public List<Post> findByNickName(String nickname) {
        return postDao.findByNickName(nickname);
    }

    @Override
    public List<Post> findByTag(String tag) {
        return postDao.findByTag(tag);
    }

    @Override
    public List<Post> findAllOrderedByHits() {
        return postDao.findAllOrderedByHits();
    }

    @Override
    public List<Post> findAllOrderedByLikes() {
        return postDao.findAllOrderedByLikes();
    }

    @Override
    public List<Post> findAllOrderedByPublishedTime() {
        return postDao.findAllOrderedByPublishedTime();
    }

    @Override
    public void onClickLikes(Long pid, boolean status) {
        if(status) {// 좋아요 활성화
            postDao.increaseLikes(pid);
            // 사용자의 즐겨찾기 목록에 등록
        } else { // 좋아요 비활성화
            postDao.decreaseLikes(pid);
            // 사용자의 즐겨찾기 목록에서 삭제
        }
    }

    @Override
    public List<String> findAllPostTags(Long pid) {
        return postDao.findAllPostTags(pid);
    }


//    @Override
//    public List<Post> findAll() {
//        return postDao.findAll();
//    }

//    @Override
//    public void update(Long id) {
//
//    }
//
//    @Override
//    public void delete(Long id) {
//
//    }
}
