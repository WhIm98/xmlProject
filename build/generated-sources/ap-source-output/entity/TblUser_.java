package entity;

import entity.TblRecommand;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-23T19:18:22")
@StaticMetamodel(TblUser.class)
public class TblUser_ { 

    public static volatile SingularAttribute<TblUser, String> password;
    public static volatile SingularAttribute<TblUser, String> fullName;
    public static volatile CollectionAttribute<TblUser, TblRecommand> tblRecommandCollection;
    public static volatile SingularAttribute<TblUser, Long> userId;

}