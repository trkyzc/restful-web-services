package com.example.restful_web_services.business.abstracts;

import com.example.restful_web_services.entities.Post;

public interface PostService {
	
	Post createPost(Post post);
	void deletePost(long id);
	Post updatePost(long id);

}
