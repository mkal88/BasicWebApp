package co.ds.mybatis.mapper;

import java.util.List;

import co.ds.bean.Topic;

public interface TopicMapper {

	List<Topic> list();
	
	void insert(Topic topic);

	void update(Topic topic);

	Topic fetch(Integer id);
}
