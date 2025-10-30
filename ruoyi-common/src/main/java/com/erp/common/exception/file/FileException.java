package com.erp.common.exception.file;

import com.erp.common.exception.BaseException;

/**
 * 文件信息异常类
 * 
 * @author erp
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
