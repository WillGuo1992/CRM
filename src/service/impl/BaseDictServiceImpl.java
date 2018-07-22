package service.impl;

import dao.BaseDictDao;
import domain.BaseDict;
import service.BaseDictService;

import java.util.List;

/**
 * @description:
 * @author: Will.Guo
 * @create: 2018-07-22 09:40
 **/
public class BaseDictServiceImpl implements BaseDictService {
    private BaseDictDao baseDictDao;
    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }

    @Override
    public List<BaseDict> findByTypeCode(String dict_type_code) {
        return baseDictDao.findByTypeCode(dict_type_code);
    }
}
