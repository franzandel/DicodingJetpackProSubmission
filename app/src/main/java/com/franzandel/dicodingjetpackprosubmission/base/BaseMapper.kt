package com.franzandel.dicodingjetpackprosubmission.base

/**
 * Created by Franz Andel on 06/03/21.
 * Android Engineer
 */

abstract class BaseMapper<SourceType, ResultType> {
    abstract fun map(dataModel: SourceType): ResultType
}