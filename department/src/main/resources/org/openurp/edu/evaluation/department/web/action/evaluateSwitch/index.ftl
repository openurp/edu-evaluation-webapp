[#ftl]
[@b.head/]
[@b.toolbar title='评教问卷开关' /]
<div class="search-container">
  <div class="search-panel">
    [@b.form name="evaluateSwitchSearchForm"  action="!search" target="evaluateSwitchlist" title="ui.searchForm" theme="search"]
      [@b.select style="width:100px" name="evaluateSwitch.semester.id" label="学年学期" items=semesters?sort_by("code")  value= currentSemester  option = "id,code" empty="..."/]
      [@b.select style="width:100px" name="evaluateSwitch.questionnaire.id" label="问卷描述" items=questionnaires option = "id,description" empty="..."/]
      [@b.select style="width:100px" name="evaluateSwitch.opened" label="开关状态" items={'true':'开启','false':'关闭'}  empty="..."/]
      <input type="hidden" name="orderBy" value="evaluateSwitch.id"/>
    [/@]
  </div>
  <div class="search-list">
      [@b.div id="evaluateSwitchlist" href="!search?evaluateSwitch.semester.id=" +currentSemester.id /]
  </div>
</div>
[@b.foot/]
