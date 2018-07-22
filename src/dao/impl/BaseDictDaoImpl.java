package dao.impl;

import dao.BaseDictDao;
import domain.BaseDict;

import java.util.List;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 09:41
 **/
public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {
    @Override
    public List<BaseDict> findByTypeCode(String dict_type_code) {
        return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code = ?",dict_type_code);
    }
}
