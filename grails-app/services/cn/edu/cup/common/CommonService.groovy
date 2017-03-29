package cn.edu.cup.common

import grails.transaction.Transactional

@Transactional
class CommonService {

    //2014.12.22 -- 标记当前页面的偏移量
    def processOffset(session, params) {
        //如果没有offset，尝试读取原来的offset--,如果有了，记住他们
        def key = calculateCurrentKey(params)
        println "processOffset ${params}"
        if (params.containsKey('offset')) {
            println "主动设置偏移量..."
            session.putValue(key, params.offset)
        } else {
            println "查询上次的偏移量..."
            params.offset = session.getValue(key)
        }
    }

    //删除记录后，必须重置偏移量
    def resetOffsetAfterDelete(session, params) {
        def key = calculateCurrentKey(params)
        session.putValue(key, 0)
    }

    def calculateCurrentKey(params) {
        def key = params.controller + "_" + params.action + "_offset"
        return key
    }

}
