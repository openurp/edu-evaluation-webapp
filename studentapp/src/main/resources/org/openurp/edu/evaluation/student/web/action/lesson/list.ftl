[#ftl]
[@b.head/]
<div class="grid">
    [@b.messages slash="4"/]
    [@b.form name="evaluateForm" action="!loadQuestionnaire" target="contentDiv"]
    <table class="gridtable">
        <thead class="gridhead">
            <tr>
                <th width="5%">课程序号</th>
                <th width="10%">课程代码</th>
                <th width="30%">课程名称</th>
                <th width="18%">开课院系</th>
                <th width="12%">课程类别</th>
                <th width="10%">教师姓名</th>
                <th width="5%">是否评教</th>
                <th width="10%">操作方式</th>
            </tr>
        </thead>
        [#if questionnaireLessons??]
        <tbody>
        [#list questionnaireLessons?sort_by(["lesson","no"]) as questionnaireLesson]
        [#if questionnaireLesson_index % 2 == 0]
            [#assign lessonClass="griddata-even"/]
        [#else]
            [#assign lessonClass="griddata-odd"/]
        [/#if]
        [#if questionnaireLesson.evaluateByTeacher]
            [#list questionnaireLesson.lesson.teachers?if_exists as teacher]
            [#if "1" == evaluateMap[questionnaireLesson.lesson.id?string + "_" + teacher.id?string]?default("0")]
                [#assign flag = true/]
            [#else]
                [#assign flag = false]
            [/#if]
            <tr class="${lessonClass!}">
                <td>${(questionnaireLesson.lesson.no)!}</td>
                <td>${(questionnaireLesson.lesson.course.code)!}</td>
                <td>${(questionnaireLesson.lesson.course.name)!}</td>
                <td>${(questionnaireLesson.lesson.teachDepart.name)!}</td>
                <td>${(questionnaireLesson.lesson.courseType.name)!}</td>
                <td>${(teacher.user.name)!}</td>
                <td>[#if flag]已评教[#else]未评教[/#if]</td>
                <td>
                    <a href="javascript:doEvaluate('${flag?string("update","evaluate")}','${(questionnaireLesson.lesson.id)!},${(teacher.id)!}')">
                    [#if flag]修改结果[#else]进行评估[/#if]
                    </a>
                </td>
            </tr>
            [/#list]
        [#else]
            [#assign flag=true]
            [#list questionnaireLesson.lesson.teachers as t]
              [#if !(evaluateMap[questionnaireLesson.lesson.id?string + "_"+ t.id])??]
                [#assign flag=false]
              [/#if]
            [/#list]
            <tr class="${lessonClass!}">
                <td>${(questionnaireLesson.lesson.no)!}</td>
                <td>${(questionnaireLesson.lesson.course.code)!}</td>
                <td>${(questionnaireLesson.lesson.course.name)!}</td>
                <td>${(questionnaireLesson.lesson.teachDepart.name)!}</td>
                <td>${(questionnaireLesson.lesson.courseType.name)!}</td>
                <td>
                [#list (questionnaireLesson.lesson.teachers)?if_exists as teacher]
                    ${(teacher.user.name)!}[#if teacher_has_next],[/#if]
                [/#list]
                </td>
                <td>[#if flag]已评教[#else]未评教[/#if]</td>
                <td>
                    <a href="javascript:doEvaluate('${flag?string("update","evaluate")}','${(questionnaireLesson.lesson.id)!}')">
                    [#if flag]修改结果[#else]进行评估[/#if]
                    </a>
                </td>
            </tr>
        [/#if]
        [/#list]    
        </tbody>
        [/#if]
    </table>
    [/@]
    [#if !questionnaireLessons??]
    <div class="gridempty" style="height: 112px;">
        <div style="padding-top: 40px;">没有查询结果</div>
    </div>
    [/#if]
</div>
<script type="text/javascript">
    function doEvaluate(value, id){
        var evaluateForm = document.evaluateForm;
        bg.form.addInput(evaluateForm, "semester.id", $("input[name='semester.id']").val());
        bg.form.addInput(evaluateForm, "evaluateState", value);
        bg.form.addInput(evaluateForm, "lessonId" ,id);
        bg.form.submit(evaluateForm);
    }
</script>
[@b.foot/]