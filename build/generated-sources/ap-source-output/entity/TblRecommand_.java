package entity;

import entity.TblProduct;
import entity.TblUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-23T19:18:22")
@StaticMetamodel(TblRecommand.class)
public class TblRecommand_ { 

    public static volatile SingularAttribute<TblRecommand, Double> productPoint;
    public static volatile SingularAttribute<TblRecommand, TblProduct> productID;
    public static volatile SingularAttribute<TblRecommand, TblProduct> productId;
    public static volatile SingularAttribute<TblRecommand, TblUser> userId;
    public static volatile SingularAttribute<TblRecommand, Long> recommandId;

}