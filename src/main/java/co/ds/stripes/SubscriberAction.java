package co.ds.stripes;

import java.util.List;

import com.google.inject.Inject;

import co.ds.bean.Subscriber;
import co.ds.bean.Topic;
import co.ds.mybatis.mapper.SubscriberMapper;
import co.ds.mybatis.mapper.SubscriberTopicMapper;
import co.ds.mybatis.mapper.TopicMapper;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

@UrlBinding("/subscriber")
public class SubscriberAction extends BaseAction implements ValidationErrorHandler {

	private static final String LIST_FORWARD = "/WEB-INF/jsp/subscriber/list.jsp";
	private static final String FORM_FORWARD = "/WEB-INF/jsp/subscriber/form.jsp";

	private SubscriberMapper subscriberMapper;
	private TopicMapper topicMapper;
	private SubscriberTopicMapper subscriberTopicMapper;

	private List<Subscriber> subscribers;
	private List<Topic> topics;

	@ValidateNestedProperties({
			@Validate(on = "save", field = "name", required = true),
			@Validate(on = "save", field = "email", required = true)
	})
	private Subscriber subscriber;

	@Inject
	public SubscriberAction(final SubscriberMapper subscriberMapper, final TopicMapper topicMapper, final SubscriberTopicMapper subscriberTopicMapper) {
		this.subscriberMapper = subscriberMapper;
		this.topicMapper = topicMapper;
		this.subscriberTopicMapper = subscriberTopicMapper;
	}

	@DefaultHandler
	public Resolution list() {
		subscribers = subscriberMapper.list();
		return new ForwardResolution(LIST_FORWARD);
	}
	/*
	 * (non-Javadoc)
	 * @see net.sourceforge.stripes.validation.ValidationErrorHandler#handleValidationErrors(net.sourceforge.stripes.validation.ValidationErrors)
	 * 
	 * Return form with topics included
	 */
	public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
		return form();
	}

	public Resolution cancelList() {
		return new RedirectResolution(HomeAction.class);
	}

	public Resolution edit() {
		subscriber = subscriberMapper.fetch(subscriber.getId());
		final List<Integer> subscriberTopics = subscriberTopicMapper.list(subscriber.getId());
		subscriber.setTopicIds(subscriberTopics);
		return form();
	}

	public Resolution form() {
		topics = topicMapper.list();
		return new ForwardResolution(FORM_FORWARD);
	}

	public Resolution cancelForm() {
		return new RedirectResolution(SubscriberAction.class);
	}

	public Resolution save() {
		if (subscriber.getId() == null) {
			subscriberMapper.insert(subscriber);
			for (final Integer topicId : subscriber.getTopicIds()) {
				subscriberTopicMapper.insert(subscriber.getId(), topicId);
			}
		} else {
			subscriberMapper.update(subscriber);
			subscriberTopicMapper.deleteForSubscriber(subscriber.getId());
			for (final Integer topicId : subscriber.getTopicIds()) {
				subscriberTopicMapper.insert(subscriber.getId(), topicId);
			}
		}
		return new RedirectResolution(SubscriberAction.class);
	}
	
	/* READ-ONLY */

	public List<Subscriber> getSubscribers() {
		return subscribers;
	}

	/* READ/WRITE */

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(final Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(final List<Topic> topics) {
		this.topics = topics;
	}
}
