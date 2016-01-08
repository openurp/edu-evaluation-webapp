[#ftl]
[@b.head/]
[@b.toolbar title='评教统计' ]
    //bar.addItem("院系评教导出","evaluateResultsExport()");
     //bar.addItem("教师评教导出","evaluateTeaResultsExport()");
[/@]

[#--[@eams.semesterBar name="project.id" semesterEmpty=false semesterName="semester.id" semesterValue=semester/]--]
<table class="indexpanel">
    <tr>
        <td class="index_view">
        [@b.form action="!search" name="textbookIndexForm" title="ui.searchForm" target="contentDiv" theme="search"]
            [#--<input type='hidden' name="semester.id" value="${semester.id}" />--]
            [@b.select name="department.id" label="院系" items=departmentList empty="..." /]
            [@b.select name="questionnaire.id" label="所用问卷" items=[] ]
                [#list questionnaires as q]
                    <option value="${q.id}">${q.description}</option>
                [/#list]
            [/@]
            [@b.select  name="searchTypes" label="查看类型" items={'1':'学院统计','2':'教师统计','3':'课程教师','4':'理论课程','5':'实践课程','7':'评教结果'} /]
        [/@]
        </td>
        
        <td class="index_content">
            [@b.div href="!search?semester.id=${(semester.id)!}" id="contentDiv"/]
        </td> 
    </tr>
    </table>
    <script language="javascript"> 
        var form =document.textbookIndexForm;
        function evaluateTeaResultsExport(){
          form.action="evaluateStatistics.action?method=evaluateTeaResultsExport";
          form.submit();
       }
       function evaluateResultsExport(){
          form.action="evaluateStatistics.action?method=evaluateResultsExport";
          form.submit();
       }
       function stuEvalutateResultsExport(){
        form.target="_blank";
           bg.form.addInput(form,"semester.id",${semester.id});
           bg.form.addInput(form,"department.id",form['department.id'].value);
           bg.form.addInput(form,"searchTypes",form['searchTypes'].value);
        bg.form.submit(form,"${b.url('!stuEvalutateResultsExport')}");
       }
     </script>
[@b.foot/]
