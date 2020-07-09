package entity;

import entity.TblProduct;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-23T19:18:22")
@StaticMetamodel(TblCategory.class)
public class TblCategory_ { 

    public static volatile CollectionAttribute<TblCategory, TblProduct> tblProductCollection;
    public static volatile SingularAttribute<TblCategory, Long> id;
    public static volatile SingularAttribute<TblCategory, String> categoryName;

}