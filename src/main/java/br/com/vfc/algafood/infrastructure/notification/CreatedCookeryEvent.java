package br.com.vfc.algafood.infrastructure.notification;

import br.com.vfc.algafood.domain.model.Cookery;
import org.springframework.context.ApplicationEvent;

public class CreatedCookeryEvent extends ApplicationEvent {
    private Cookery cookery;

    public CreatedCookeryEvent(Cookery cookery) {
        super(cookery);
        this.cookery = cookery;
    }

    public Cookery getCookery() {
        return cookery;
    }
}
