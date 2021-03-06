/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.evaluation.clazz.web.action

import java.time.LocalDate

import org.beangle.commons.collection.Order
import org.beangle.data.dao.OqlBuilder
import org.beangle.webmvc.api.view.View
import org.openurp.base.model.Department
import org.openurp.base.edu.model.{Project, Semester}
import org.openurp.edu.evaluation.clazz.model.TextEvaluation

class TextEvaluationAction extends ProjectRestfulAction[TextEvaluation] {

  override protected def indexSetting(): Unit = {
    put("project",getProject)
    put("currentSemester", getCurrentSemester)
    put("departments", findInSchool(classOf[Department]))
  }

  override def search(): View = {
    // 页面条件
    val semesterQuery = OqlBuilder.from(classOf[Semester], "semester").where(":now between semester.beginOn and semester.endOn", LocalDate.now)
    val semesterId = getInt("semester.id").getOrElse(entityDao.search(semesterQuery).head.id)
    val semester = entityDao.get(classOf[Semester], semesterId)
    val state = getBoolean("audited").getOrElse(null)
    val textEvaluation = OqlBuilder.from(classOf[TextEvaluation], "textEvaluation")
    textEvaluation.orderBy(get(Order.OrderStr).orNull).limit(getPageLimit)
    if (state != null)
      textEvaluation.where("textEvaluation.audited=:state", state)
    textEvaluation.where("textEvaluation.clazz.semester=:semester", semester)
    put("textEvaluations", entityDao.search(textEvaluation))
    forward()
  }

  /**
   * 修改(是否确认)
   *
   * @return
   */
  def updateAffirm(): View = {
    val ids = longIds(simpleEntityName)
    val state = getBoolean("state").get

    val textEvaluations = entityDao.find(classOf[TextEvaluation], ids)
    textEvaluations foreach { textEvaluation =>
      textEvaluation.audited = state
    }
    entityDao.saveOrUpdate(textEvaluations)
    redirect("search", "info.action.success")
  }
}
