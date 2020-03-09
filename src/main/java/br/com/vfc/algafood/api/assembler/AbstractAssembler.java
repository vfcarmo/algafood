package br.com.vfc.algafood.api.assembler;

import br.com.vfc.algafood.domain.model.BaseEntity;
import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract class which has a default implementation of converters from Input to Domain and from Domain to Model.
 * @param <I> Input type (Model type for input data).
 * @param <D> Domain type.
 * @param <M> Model type
 */
public abstract class AbstractAssembler<I, D extends BaseEntity, M> {

    protected ModelMapper modelMapper;

    private Class<M> modelClass;

    private Class<D> domainClass;

    /**
     * Construtor.
     * @param modelMapper Mapper responsible for conversions.
     */
    public AbstractAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        this.domainClass = (Class<D>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        this.modelClass = (Class<M>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }

    /**
     * Converts from Input to Domain.
     * @param input Input model object.
     * @return Domain object.
     */
    public D toDomain(I input) {
        return modelMapper.map(input, domainClass);
    }

    /**
     * Converts from Input model object to Domain object and sets the id value.
     * @param id Identity of domain class.
     * @param input Input model object.
     * @return Domain object.
     */
    public D toDomain(Long id, I input) {
        D domain = toDomain(input);
        domain.setId(id);
        return domain;
    }

    /**
     * Converts from Domain object to Model object.
     * @param domain Domain object.
     * @return Model object
     */
    public M toModel(D domain) {
        return modelMapper.map(domain, modelClass);
    }

    /**
     * Converts a collection of domain objects to another collection of model objects.
     * @param collection Collection of domain objects.
     * @return List of model objects.
     */
    public List<M> toCollectionModel(Collection<D> collection) {
        return collection.stream()
                .map(domain -> toModel(domain))
                .collect(Collectors.toList());
    }

    /**
     * Copy the properties from the source object to target object.
     * @param source source object.
     * @param target target object.
     */
    public void copyProperties(Object source, Object target) {
        modelMapper.map(source, target);
    }
}
