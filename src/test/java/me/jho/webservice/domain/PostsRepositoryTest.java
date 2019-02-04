package me.jho.webservice.domain;

import me.jho.webservice.domain.posts.Posts;
import me.jho.webservice.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        /**
         * 이후 테스트 코드에 영향을 미치지 않게 하기 위하여
         * 테스트 메소드가 끝날 때마다 repository 전체를 비우는 코드
         */
        postsRepository.deleteAll();

    }

    @Test
    public void 게시글저장_물러오기() {
        // given
        postsRepository.save(Posts.builder()
                .title("테스트 게시글ㅇㅇ")
                .content("테스트용 본문")
                .author("jooho_lee@outlook.kr")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트 게시글ㅇㅇ"));
        assertThat(posts.getContent(), is("테스트용 본문"));
    }

}
