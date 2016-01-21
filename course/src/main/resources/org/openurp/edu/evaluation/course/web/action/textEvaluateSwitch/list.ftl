[#ftl]
[@b.head/]
[@b.form name="textEvaluateSwitchSearchForm" action="!search" target="contentDiv"]
    [@b.grid items=textEvaluationSwitchs var="evaluateSwitch" sortable="true"]    
        [@b.gridbar title="文字评教开关列表"]
            bar.addItem("${b.text('action.add')}",action.add());
            bar.addItem("${b.text('action.edit')}",action.edit());
            bar.addItem("${b.text('action.delete')}",action.remove());
        [/@]
        [@b.row]
            [@b.boxcol/]
            [@b.col width="10%" property="semester.code" title="学年学期"]${(stdEvaluateSwitch.semester.code)!}[/@]
            [@b.col property="opened" title="是否开放"]${(evaluateSwitch.opened?string("开放","关闭"))!}[/@]
            [@b.col property="beginAt" title="开始时间"]${(evaluateSwitch.beginAt?string("yyyy-MM-dd HH:mm"))!}[/@]
            [@b.col property="endAt" title="结束时间"]${(evaluateSwitch.endAt?string("yyyy-MM-dd HH:mm"))!}[/@]
            [@b.col title="教师查询"]${(evaluateSwitch.openedTeacher?string("开放","关闭"))!}[/@]
            [@b.col title="学生文字评教[手动设置,不限时间]"]${(evaluateSwitch.textEvaluateOpened?string("开放","关闭"))!}[/@]
        [/@]
    [/@]
[/@]
[@b.foot/]

