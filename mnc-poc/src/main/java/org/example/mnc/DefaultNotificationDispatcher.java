package org.example.mnc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class DefaultNotificationDispatcher implements NotificationDispatcher<Notification> {

    private Collection<ChannelProvider> channelProviders;

    @Override
    public Optional<Object> dispatch(Notification notification) {

        Assert.state(channelProviders != null, "channelProviders cannot be null!");

        for (ChannelProvider provider : channelProviders) {
            if (provider.supports(notification)) {
                return provider.dispatch(notification);
            }
        }

        return Optional.empty();
    }

    @Override
    public void setChannelProviders(Collection<ChannelProvider> providers) {
        if (providers != null) {
            this.channelProviders = providers;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ChannelProvider> beansOfType = applicationContext.getBeansOfType(ChannelProvider.class);
        setChannelProviders(beansOfType.values());
    }
}
