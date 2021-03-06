[#ftl]
[@b.head/]
[@b.toolbar title='管理文字评教' id='textEvaluationBar' /]
<div class="search-container">
  <div class="search-panel">
        [@b.form action="!search" name="textEvaluationIndexForm" title="ui.searchForm" target="contentDiv" theme="search"]
            <input type="hidden" name="clazz.project.id" value="${(project.id)!}"/>
            [@urp_base.semester  name="semester.id" label="学年学期" value=currentSemester/]
            [@b.textfields names="clazz.crn;课程序号,clazz.course.code;课程代码,clazz.course.name;课程名称,teacher.user.name;教师名称"/]
            [@b.select name="clazz.teachDepart.id" label="开课院系" items=departments empty="..."/]
            [@b.select name="audited" label="是否确认" items={'1':'已确认','0':'未确认'}  empty="..."/]
        [/@]
  </div>
  <div class="search-list">
            [@b.div id="contentDiv" href="!search?semester.id="+currentSemester.id/]
  </div>
</div>
[@b.foot/]

[#--
<#include "/template/head.ftl"/>
<BODY topmargin=0 leftmargin=0>
  <table id="bar" width="100%"></table>
   <table  class="frameTable_title">
      <tr>
       <td  style="width:50px" >
          <font color="blue"><@text name="action.advancedQuery"/></font>
       </td>
       <td>|</td>
      <form name="evaluateForm" method="post" action="textEvaluation.action?method=index" action="" >
      <input type="hidden" name="textEvaluation.semester.id" value="${semester.id}" />
      <#include "/template/time/semester.ftl"/>
     </tr>
   </table>
    <table   width="100%"  class="frameTable">
        <tr>
            <td width="20%" class="frameTable_view" valign="top">
                <#include "searchTable.ftl"/>
                </form>
            </td>
            <td valign="top">
                <iframe name="displayFrame" src="#" width="100%" frameborder="0" scrolling="no"></iframe>
            </td>
        </tr>
    </table>
    <script language="javascript">
        function query(form){
            form.action="textEvaluateResult.action?method=search";
            form.target="displayFrame";
            form.submit();
        }
       query(document.evaluateForm);
       var bar = new ToolBar('bar','<@text name="textEvaluation.idea"/>',null,true,true);
    </script>
</body>
<#include "/template/foot.ftl"/>
--]
