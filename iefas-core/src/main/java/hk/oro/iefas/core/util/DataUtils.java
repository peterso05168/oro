package hk.oro.iefas.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingBuilder;
import org.dozer.loader.api.TypeMappingOption;

/**
 * @version $Revision: 2796 $ $Date: 2018-05-31 18:35:52 +0800 (週四, 31 五月 2018) $
 * @author $Author: marvel.ma $
 */
public final class DataUtils {

    private static DozerBeanMapper mapper = new DozerBeanMapper();

    public static <T> T copyProperties(Object source, Class<T> destinationClass) {
        return copyProperties(source, destinationClass, null);
    }

    public static <T> List<T> copyPropertiesForList(List<?> sourceList, Class<T> destinationClass) {
        List<T> list = new ArrayList<>();
        sourceList.stream().forEach(s -> {
            list.add(copyProperties(s, destinationClass, null));
        });
        return list;
    }

    public static void copyProperties(Object source, Object destination) {
        copyProperties(source, destination, null);
    }

    public static <T> T copyProperties(final Object source, final Class<T> destinationClass,
        final List<String> excludeFields) {
        DozerBeanMapper mapper = DataUtils.mapper;
        if (CommonUtils.isNotEmpty(excludeFields)) {
            mapper = new DozerBeanMapper();
            BeanMappingBuilder bmb = new BeanMappingBuilder() {
                @Override
                protected void configure() {
                    TypeMappingBuilder tmb
                        = this.mapping(source.getClass(), destinationClass, new TypeMappingOption[0]);
                    Iterator<String> iterator = excludeFields.iterator();

                    while (iterator.hasNext()) {
                        String excludeField = iterator.next();
                        tmb.exclude(excludeField);
                    }

                }
            };
            mapper.addMapping(bmb);
        }

        return mapper.map(source, destinationClass);
    }

    public static void copyProperties(final Object source, final Object destination, final List<String> excludeFields) {
        DozerBeanMapper mapper = DataUtils.mapper;
        if (CommonUtils.isNotEmpty(excludeFields)) {
            mapper = new DozerBeanMapper();
            BeanMappingBuilder bmb = new BeanMappingBuilder() {
                @Override
                protected void configure() {
                    TypeMappingBuilder tmb
                        = this.mapping(source.getClass(), destination.getClass(), new TypeMappingOption[0]);
                    Iterator<String> iterator = excludeFields.iterator();

                    while (iterator.hasNext()) {
                        String excludeField = iterator.next();
                        tmb.exclude(excludeField);
                    }

                }
            };
            mapper.addMapping(bmb);
        }

        mapper.map(source, destination);
    }

}
