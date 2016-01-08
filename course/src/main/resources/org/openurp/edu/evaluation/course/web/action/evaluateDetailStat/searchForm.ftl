[#ftl]
[@b.head/]
[#--[@eams.semesterBar semesterValue=semester name="project.id" semesterName="semester.id" semesterEmpty="false" initCallback="changeSemester()"/]--]
<table class="indexpanel">
    <tr>
        <td style="width:200px" class="index_view">
        [@b.form action="!search" name="evaluateTeacherStatIndexForm" title="ui.searchForm" target="contentDiv" theme="search"]
            <input type="hidden" name="searchFormFlag" value="${searchFormFlag!}"/>
            [#if searchFormFlag?? || searchFormFlag == "beenStat"]
            <input type="hidden" name="evaluateTeacherStat.lesson.project.id" value="${(project.id)!}"/>
            [@b.textfields style="width:130px" names="evaluateTeacherStat.lesson.course.code;课程代码,evaluateTeacherStat.lesson.course.name;课程名称,evaluateTeacherStat.teacher.code;教师工号,evaluateTeacherStat.teacher.person.name.formatedName;教师名称"/]
            [@b.select style="width:134px" name="evaluateTeacherStat.depart.id" label="开课院系" items=departments empty="..."/]
            [@b.select style="width:134px" name="evaluateTeacherStat.questionnaire.id" label="问卷类型" items=questionnaires option="id,description" empty="..."/]
            [#else]
            <input type="hidden" name="evaluateResult.lesson.project.id" value="${(project.id)!}"/>
            [@b.textfields style="width:130px" names="evaluateResult.lesson.course.code;课程代码,evaluateResult.lesson.course.name;课程名称,evaluateResult.teacher.code;教师工号,evaluateResult.teacher.name;教师名称"/]
            [@b.select style="width:134px" name="evaluateResult.depart.id" label="开课院系" items=departments empty="..."/]
            [@b.select style="width:134px" name="evaluateResult.questionnaire.id" label="问卷类型" items=questionnaires option="id,description" empty="..."/]
            [/#if]
        [/@]
        </td>
        <td class="index_content">
            [@b.div id="contentDiv"/]
        </td> 
    </tr>
</table>
<script type="text/javaScript">
    [#if !searchFormFlag?? || searchFormFlag == "beenStat"]
    var form = document.evaluateTeacherStatIndexForm;
    
    function changeSemester(){
        bg.form.addInput(form, "evaluateTeacherStat.semester.id", $("input[name='semester.id']").val());
        bg.form.submit(form);
    }
    [#else]
    var form = document.evaluateResultIndexForm;
    
    function changeSemester(){
        bg.form.addInput(form, "evaluateResult.semester.id", $("input[name='semester.id']").val());
        bg.form.submit(form);
    }
    [/#if]
</script>
[@b.foot/]

