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
package org.openurp.edu.evaluation.app.course.model

import org.beangle.data.model.IntId
import org.beangle.data.model.pojo.TemporalAt
import org.openurp.base.edu.model.{Project, Semester}

class StdEvaluateSwitch extends IntId with TemporalAt {

  var semester: Semester = _
  var opened: Boolean = false
  var project: Project = _

  def isOpenedAt(d: java.time.Instant): Boolean = {
    if (d.isBefore(this.beginAt)) false
    if (this.endAt.get.isBefore(d)) false
    opened
  }
}
