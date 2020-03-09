package br.com.vfc.algafood.infrastructure.notification.listener;

import br.com.vfc.algafood.infrastructure.notification.CreatedCookeryEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @EventListener
    public void createdCookeryListener(CreatedCookeryEvent event) {
        System.out.println(String.format("The cookery %s was created.", event.getCookery().getName()));
    }
}
