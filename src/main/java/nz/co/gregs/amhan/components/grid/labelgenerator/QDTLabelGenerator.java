/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.*;

/**
 *
 * @author gregorygraham
 */
public class QDTLabelGenerator {

	private QDTLabelGenerator() {
	}

	public static <A extends DBRow, Q extends QueryableDatatype<T>, T> AbstractDBRowPropertyLabelGenerator<A, Q> getLabelGenerator(A example, Q qdt) {
		return getLabelGeneratorForQDT(example, qdt);
	}

	@SuppressWarnings("unchecked")
	private static <A extends DBRow, Q extends QueryableDatatype> AbstractDBRowPropertyLabelGenerator<A, Q> getLabelGeneratorForQDT(A example, Q qdt) {
		if (qdt instanceof DBBoolean) {
			return new DBBooleanLabelGenerator(example, (DBBoolean) qdt);
		} else if (qdt instanceof DBBooleanArray) {
			return new DBBooleanArrayLabelGenerator(example, (DBBooleanArray) qdt);
		} else if (qdt instanceof DBDate) {
			return new DBDateLabelGenerator(example, (DBDate) qdt);
		} else if (qdt instanceof DBDateOnly) {
			return new DBDateOnlyLabelGenerator(example, (DBDateOnly) qdt);
		} else if (qdt instanceof DBDateRepeat) {
			return new DBDateRepeatLabelGenerator(example, (DBDateRepeat) qdt);
		} else if (qdt instanceof DBDuration) {
			return new DBDurationLabelGenerator(example, (DBDuration) qdt);
		} else if (qdt instanceof DBEncryptedText) {
			return new DBEncryptedTextLabelGenerator(example, (DBEncryptedText) qdt);
		} else if (qdt instanceof DBEnum) {
			return new DBEnumLabelGenerator(example, (DBEnum) qdt);
		} else if (qdt instanceof DBInstant) {
			return new DBInstantLabelGenerator(example, (DBInstant) qdt);
		} else if (qdt instanceof DBInteger) {
			return new DBIntegerLabelGenerator(example, (DBInteger) qdt);
		} else if (qdt instanceof DBIntegerEnum) {
			return new DBIntegerEnumLabelGenerator(example, (DBIntegerEnum) qdt);
		} else if (qdt instanceof DBJavaObject) {
			return new DBJavaObjectLabelGenerator(example, (DBJavaObject) qdt);
		} else if (qdt instanceof DBLargeBinary) {
			return new DBLargeBinaryLabelGenerator(example, (DBLargeBinary) qdt);
		} else if (qdt instanceof DBLargeObject) {
			return new DBLargeObjectLabelGenerator(example, (DBLargeObject) qdt);
		} else if (qdt instanceof DBLargeText) {
			return new DBLargeTextLabelGenerator(example, (DBLargeText) qdt);
		} else if (qdt instanceof DBLocalDate) {
			return new DBLocalDateLabelGenerator(example, (DBLocalDate) qdt);
		} else if (qdt instanceof DBLocalDateTime) {
			return new DBLocalDateTimeLabelGenerator(example, (DBLocalDateTime) qdt);
		} else if (qdt instanceof DBNumber) {
			return new DBNumberLabelGenerator(example, (DBNumber) qdt);
		} else if (qdt instanceof DBNumberStatistics) {
			return new DBNumberStatisticsLabelGenerator(example, (DBNumberStatistics) qdt);
		} else if (qdt instanceof DBPasswordHash) {
			return new DBPasswordHashLabelGenerator(example, (DBPasswordHash) qdt);
		} else if (qdt instanceof DBStatistics) {
			return new DBStatisticsLabelGenerator(example, (DBStatistics) qdt);
		} else if (qdt instanceof DBString) {
			return new DBStringLabelGenerator(example, (DBString) qdt);
		} else if (qdt instanceof DBStringEnum) {
			return new DBStringEnumLabelGenerator(example, (DBStringEnum) qdt);
		} else if (qdt instanceof DBStringTrimmed) {
			return new DBStringTrimmedLabelGenerator(example, (DBStringTrimmed) qdt);
		} else if (qdt instanceof DBUUID) {
			return new DBUUIDLabelGenerator(example, (DBUUID) qdt);
		} else if (qdt instanceof DBUnknownDatatype) {
			return new DBUnknownDatatypeLabelGenerator(example, (DBUnknownDatatype) qdt);
		} else if (qdt instanceof DBUntypedValue) {
			return new DBUntypedValueLabelGenerator(example, (DBUntypedValue) qdt);
		} else {
			return new DefaultQDTLabelGenerator(example, qdt);
		}
	}

}
