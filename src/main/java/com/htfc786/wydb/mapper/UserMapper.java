package com.htfc786.wydb.mapper;

import com.htfc786.wydb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    public User getUserById(@Param("id") int id);

    public List<User> getUser();
}
