/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.evaluation.lesson.model

import scala.reflect.runtime.universe

import org.beangle.data.model.bind.Mapping
import org.openurp.edu.evaluation.lesson.result.model.EvaluateResult
import org.openurp.edu.evaluation.lesson.result.model.QuestionResult
import org.openurp.edu.evaluation.lesson.stat.model.CourseEvalStat
import org.openurp.edu.evaluation.lesson.stat.model.CourseOptionStat
import org.openurp.edu.evaluation.lesson.stat.model.CourseQuestionStat
import org.openurp.edu.evaluation.lesson.stat.model.CourseQuestionTypeStat
import org.openurp.edu.evaluation.lesson.stat.model.DepartEvalStat
import org.openurp.edu.evaluation.lesson.stat.model.DepartOptionStat
import org.openurp.edu.evaluation.lesson.stat.model.DepartQuestionStat
import org.openurp.edu.evaluation.lesson.stat.model.DepartQuestionTypeStat
import org.openurp.edu.evaluation.lesson.stat.model.EvalStat
import org.openurp.edu.evaluation.lesson.stat.model.LessonEvalStat
import org.openurp.edu.evaluation.lesson.stat.model.LessonOptionStat
import org.openurp.edu.evaluation.lesson.stat.model.LessonQuestionStat
import org.openurp.edu.evaluation.lesson.stat.model.LessonQuestionTypeStat
import org.openurp.edu.evaluation.lesson.stat.model.OptionStat
import org.openurp.edu.evaluation.lesson.stat.model.QuestionStat
import org.openurp.edu.evaluation.lesson.stat.model.QuestionTypeStat
import org.openurp.edu.evaluation.lesson.stat.model.SchoolEvalStat
import org.openurp.edu.evaluation.lesson.stat.model.SchoolOptionStat
import org.openurp.edu.evaluation.lesson.stat.model.SchoolQuestionStat
import org.openurp.edu.evaluation.lesson.stat.model.SchoolQuestionTypeStat
import org.openurp.edu.evaluation.lesson.stat.model.TeacherEvalStat
import org.openurp.edu.evaluation.lesson.stat.model.TeacherOptionStat
import org.openurp.edu.evaluation.lesson.stat.model.TeacherQuestionStat
import org.openurp.edu.evaluation.lesson.stat.model.TeacherQuestionTypeStat

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("date")

    //lesson result
    bind[EvaluateResult].on(e => declare(
      e.lesson & e.student & e.department & e.questionnaire & e.evaluateAt are notnull,
      e.questionResults is depends("result"),
      e.remark is length(20)))

    bind[QuestionResult].on(e => declare(
      e.result & e.questionType & e.question are notnull))

    // department eval and questionanire
    bind[DepartEvaluation].on(e => declare(
      e.staff & e.semester & e.course & e.userName & e.updateAt & e.score are notnull))

    bind[QuestionnaireLesson].on(e => declare(
      e.lesson & e.questionnaire are notnull))

    //eval stat
    bind[EvalStat].on(e => declare(
      e.semester & e.statAt & e.questionnaire are notnull))

    bind[OptionStat].on(e => declare(
      e.option is notnull))

    bind[QuestionStat].on(e => declare(
      e.question is notnull))

    bind[QuestionTypeStat].on(e => declare(
      e.questionType is notnull))

    //course stat
    bind[CourseEvalStat].on(e => declare(
      e.course & e.staff are notnull,
      e.questionStats is depends(classOf[CourseQuestionStat], "evalStat"),
      e.questionTypeStats is depends(classOf[CourseQuestionTypeStat], "evalStat")))

    bind[CourseOptionStat].on(e => declare(
      e.questionStat is (notnull, target[CourseQuestionStat])))

    bind[CourseQuestionStat].on(e => declare(
      e.evalStat is (notnull, target[CourseEvalStat]),
      e.optionStats is depends(classOf[CourseOptionStat], "questionStat")))

    bind[CourseQuestionTypeStat].on(e => declare(
      e.evalStat is (notnull, target[CourseEvalStat])))

    //depart stat
    bind[DepartEvalStat].on(e => declare(
      e.department is notnull,
      e.questionStats is depends(classOf[DepartQuestionStat], "evalStat"),
      e.questionTypeStats is depends(classOf[DepartQuestionTypeStat], "evalStat")))

    bind[DepartOptionStat].on(e => declare(
      e.questionStat is (notnull, target[DepartQuestionStat])))

    bind[DepartQuestionStat].on(e => declare(
      e.evalStat is (notnull, target[DepartEvalStat]),
      e.optionStats is depends(classOf[DepartOptionStat], "questionStat")))

    bind[DepartQuestionTypeStat].on(e => declare(
      e.evalStat is (notnull, target[DepartEvalStat])))

    // lesson stat
    bind[LessonEvalStat].on(e => declare(
      e.lesson is notnull,
      e.staff is notnull,
      e.questionStats is depends(classOf[LessonQuestionStat], "evalStat"),
      e.questionTypeStats is depends(classOf[LessonQuestionTypeStat], "evalStat")))

    bind[LessonOptionStat].on(e => declare(
      e.questionStat is (notnull, target[LessonQuestionStat])))

    bind[LessonQuestionStat].on(e => declare(
      e.evalStat is (notnull, target[LessonEvalStat]),
      e.optionStats is depends(classOf[LessonOptionStat], "questionStat")))

    bind[LessonQuestionTypeStat].on(e => declare(
      e.evalStat is (notnull, target[LessonEvalStat])))

    //school
    bind[SchoolEvalStat].on(e => declare(
      e.questionStats is depends(classOf[SchoolQuestionStat], "evalStat"),
      e.questionTypeStats is depends(classOf[SchoolQuestionTypeStat], "evalStat")))

    bind[SchoolOptionStat].on(e => declare(
      e.questionStat is (notnull, target[SchoolQuestionStat])))

    bind[SchoolQuestionStat].on(e => declare(
      e.evalStat is (notnull, target[SchoolEvalStat]),
      e.optionStats is depends(classOf[SchoolOptionStat], "questionStat")))

    bind[SchoolQuestionTypeStat].on(e => declare(
      e.evalStat is (notnull, target[SchoolEvalStat])))

    //staff
    bind[TeacherEvalStat].on(e => declare(
      e.staff is notnull,
      e.questionStats is depends(classOf[TeacherQuestionStat], "evalStat"),
      e.questionTypeStats is depends(classOf[TeacherQuestionTypeStat], "evalStat")))

    bind[TeacherOptionStat].on(e => declare(
      e.questionStat is (notnull, target[TeacherQuestionStat])))

    bind[TeacherQuestionStat].on(e => declare(
      e.evalStat is (notnull, target[TeacherEvalStat]),
      e.optionStats is depends(classOf[TeacherOptionStat], "questionStat")))

    bind[TeacherQuestionTypeStat].on(e => declare(
      e.evalStat is (notnull, target[TeacherEvalStat])))

  }

}