package com.bawi.dozer.xml;

import java.util.List;

import org.dozer.DozerConverter;

import com.bawi.dozer.DestinationData.Children;
import com.bawi.dozer.SourceData.Child;

public class ChildrenCustomConverter extends DozerConverter<List<Child>, Children> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ChildrenCustomConverter() {
        super((Class)List.class, Children.class);
    }

    @Override
    public List<Child> convertFrom(Children arg0, List<Child> arg1) {
        return null;
    }

    @Override
    public Children convertTo(List<Child> sourceChildren, Children destinationChildren) {
        destinationChildren = new Children();
        StringBuilder stringBuilder = new StringBuilder();
        for (Child sourceChild : sourceChildren) {
            stringBuilder.append(sourceChild.getName() + ',');
        }
        String names = stringBuilder.toString();
        destinationChildren.setNames(names);
        return destinationChildren;
    }
}
