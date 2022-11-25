package org.example.dto;


import lombok.Data;
import org.example.model.Post;

import java.util.List;

@Data
public class BigUserDTO {
    private String name;
    private String password;
    private List<Post> posts;
}
