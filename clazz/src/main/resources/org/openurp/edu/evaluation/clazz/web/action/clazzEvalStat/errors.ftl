<#include "/template/head.ftl"/>
<table id="taskListBar" width="100%"> </table>
<script>
   var bar = new ToolBar("taskListBar","问卷评教",null,true,true);
   bar.addBack("<@text name="action.back"/>");
</script>
   <table width="40%" height="100%">
     <tr>
       <td valign="top"><@getMessage/></td>
     </tr>
   </table>
</body>
<#include "/template/foot.ftl"/>
