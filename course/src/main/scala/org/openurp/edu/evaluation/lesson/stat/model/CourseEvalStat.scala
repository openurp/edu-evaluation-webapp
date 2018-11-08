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
package org.openurp.edu.evaluation.clazz.stat.model

import org.beangle.data.model.LongId
import org.openurp.edu.base.model.Course
import org.openurp.edu.base.model.Teacher
import org.openurp.edu.evaluation.clazz.stat.model.EvalStat
import org.openurp.edu.evaluation.clazz.stat.model.OptionStat
import org.openurp.edu.evaluation.clazz.stat.model.QuestionStat
import org.openurp.edu.evaluation.clazz.stat.model.QuestionTypeStat
import org.openurp.edu.evaluation.clazz.stat.model.Rank

class CourseEvalStat extends LongId with EvalStat with Rank {

  var teacher: Teacher = _
  var course: Course = _

}

class CourseOptionStat extends LongId with OptionStat

class CourseQuestionStat extends LongId with QuestionStat

class CourseQuestionTypeStat extends LongId with QuestionTypeStat
