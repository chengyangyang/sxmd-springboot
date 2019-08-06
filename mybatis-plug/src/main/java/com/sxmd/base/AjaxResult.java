package com.sxmd.base;

/**
 * Description:
 *
 * @author cy
 * @date 2019年08月06日 15:52
 * Version 1.0
 */
public class AjaxResult {

    public static final int AJAX_STATUS_SUCCESS = 0;
    public static final int AJAX_STATUS_ERROR = -999;
    public static final String AJAX_MESSAGE_SUCCESS = "success";
    private String message;
    private Object results;
    private int statuscode;

    public AjaxResult() {
    }

    public AjaxResult(int statuscode) {
        this.statuscode = statuscode;
    }

    public AjaxResult(String message) {
        this.message = message;
    }

    public Object getResults() {
        return this.results;
    }

    public AjaxResult setResults(Object results) {
        this.results = results;
        return this;
    }

    public int getStatuscode() {
        return this.statuscode;
    }

    public AjaxResult setStatuscode(int statuscode) {
        this.statuscode = statuscode;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public AjaxResult addSuccess(String message) {
        this.statuscode = 0;
        this.message = message;
        return this;
    }

    public AjaxResult addError(String message) {
        this.statuscode = -999;
        this.message = message;
        this.results = null;
        return this;
    }

    public AjaxResult addFail(String message) {
        this.statuscode = -999;
        this.message = message;
        this.results = null;
        return this;
    }

    public AjaxResult addWarn(String message) {
        this.statuscode = -999;
        this.message = message;
        this.results = null;
        return this;
    }

    public AjaxResult success(Object data) {
        this.message = "success";
        this.results = data;
        return this;
    }

    public static class PAjaxResult extends AjaxResult {
        private int pageNum;
        private int pageSize;
        private long total;

        public PAjaxResult() {
            super(0);
        }

        public PAjaxResult(int status) {
            super(status);
        }

        public PAjaxResult(int pageNum, int pageSize, long total, Object results) {
            this();
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            this.total = total;
            super.results = results;
        }

        public int getPageNum() {
            return this.pageNum;
        }

        public AjaxResult.PAjaxResult setPageNum(int pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public AjaxResult.PAjaxResult setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public long getTotal() {
            return this.total;
        }

        public AjaxResult.PAjaxResult setTotal(long total) {
            this.total = total;
            return this;
        }
    }
}
