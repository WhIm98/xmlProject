package entity;

import entity.TblCategory;
import entity.TblRecommand;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-23T19:18:22")
@StaticMetamodel(TblProduct.class)
public class TblProduct_ { 

    public static volatile SingularAttribute<TblProduct, String> imageLink;
    public static volatile SingularAttribute<TblProduct, Double> price;
    public static volatile CollectionAttribute<TblProduct, TblRecommand> tblRecommandCollection;
    public static volatile SingularAttribute<TblProduct, String> productLink;
    public static volatile SingularAttribute<TblProduct, Long> id;
    public static volatile SingularAttribute<TblProduct, String> productName;
    public static volatile SingularAttribute<TblProduct, TblCategory> categoryID;

}