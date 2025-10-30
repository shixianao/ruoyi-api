package com.erp.biz.student.service;

import com.erp.biz.student.domain.BizStudent;
import com.erp.biz.student.vo.BizStudentVo;
import com.erp.biz.student.bo.BizStudentQueryBo;
import com.erp.biz.student.bo.BizStudentAddBo;
import com.erp.biz.student.bo.BizStudentEditBo;
import com.erp.common.core.service.DiBaseService;
import com.erp.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 学生管理Service接口
 *
 * @author erp
 * @date 2025-10-30
 */
public interface IBizStudentService extends DiBaseService<BizStudent> {
    /**
     * 查询单个
     * @return
     */
    BizStudentVo queryById(Long id);

    /**
     * 查询列表
     */
    TableDataInfo<BizStudentVo> queryPageList(BizStudentQueryBo bo);

    /**
     * 查询列表
     */
    List<BizStudentVo> queryList(BizStudentQueryBo bo);

    /**
     * 根据新增业务对象插入学生管理
     * @param bo 学生管理新增业务对象
     * @return
     */
    Boolean insertByAddBo(BizStudentAddBo bo);

    /**
     * 根据编辑业务对象修改学生管理
     * @param bo 学生管理编辑业务对象
     * @return
     */
    Boolean updateByEditBo(BizStudentEditBo bo);

    /**
     * 批量删除学生管理
     * @param ids 主键集合
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}