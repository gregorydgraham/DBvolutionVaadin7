/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import com.vaadin.flow.data.renderer.Renderer;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.*;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapperDefinition;

/**
 *
 * @author gregorygraham
 */
public class DBRowPropertyRenderer {

	private DBRowPropertyRenderer() {

	}

	public static <A extends DBRow> Renderer<A> getRenderer(A example, PropertyWrapper wrapper) {
		return getRendererForQDT(example, wrapper.getPropertyWrapperDefinition(), wrapper.getQueryableDatatype());
	}

	public static <A extends DBRow> DBLocalDateTimeRenderer<A> getRenderer(A example, DBLocalDateTime qdt) {
		return new DBLocalDateTimeRenderer<A>(example, qdt);
	}

	public static <A extends DBRow> DBStringRenderer<A> getRenderer(A example, DBString qdt) {
		return new DBStringRenderer<A>(example, qdt);
	}

	@SuppressWarnings("unchecked")
	private static <A extends DBRow> Renderer<A> getRendererForQDT(A example, PropertyWrapperDefinition pwDefn, QueryableDatatype<?> qdt) {
		if (qdt instanceof DBBoolean) {
			return new DBBooleanRenderer<A>(example, (DBBoolean) qdt);
		} else if (qdt instanceof DBBooleanArray) {
			return new DBBooleanArrayRenderer<A>(example, (DBBooleanArray) qdt);
		} else if (qdt instanceof DBDateOnly) {
			return new DBDateOnlyRenderer<A>(example, (DBDateOnly) qdt);
		} else if (qdt instanceof DBDateRepeat) {
			return new DBDateRepeatRenderer<A>(example, (DBDateRepeat) qdt);
		} else if (qdt instanceof DBDuration) {
			return new DBDurationRenderer<A>(example, (DBDuration) qdt);
		} else if (qdt instanceof DBEncryptedText) {
			return new DBEncryptedTextRenderer<A>(example, (DBEncryptedText) qdt);
		} else if (qdt instanceof DBInstant) {
			return new DBInstantRenderer<A>(example, (DBInstant) qdt);
		} else if (qdt instanceof DBInteger) {
			return new DBIntegerRenderer<A>(example, (DBInteger) qdt);
		} else if (qdt instanceof DBIntegerEnum) {
			return new DBIntegerEnumRenderer(example, (DBIntegerEnum) qdt);
		} else if (qdt instanceof DBJavaObject) {
			return new DBJavaObjectRenderer(example, (DBJavaObject) qdt);
		} else if (qdt instanceof DBLargeBinary) {
			return new DBLargeBinaryRenderer<A>(example, (DBLargeBinary) qdt);
		} else if (qdt instanceof DBLocalDate) {
			return new DBLocalDateRenderer<A>(example, (DBLocalDate) qdt);
		} else if (qdt instanceof DBLocalDateTime) {
			return new DBLocalDateTimeRenderer<A>(example, (DBLocalDateTime) qdt);
		} else if (qdt instanceof DBNumberStatistics) {
			return new DBNumberStatisticsRenderer<A>(example, (DBNumberStatistics) qdt);
		} else if (qdt instanceof DBPasswordHash) {
			return new DBPasswordHashRenderer<A>(example, (DBPasswordHash) qdt);
		} else if (qdt instanceof DBStatistics) {
			return new DBStatisticsRenderer(example, (DBStatistics) qdt);
		} else if (qdt instanceof DBStringEnum) {
			return new DBStringEnumRenderer(example, (DBStringEnum) qdt);
		} else if (qdt instanceof DBStringTrimmed) {
			return new DBStringTrimmedRenderer<A>(example, (DBStringTrimmed) qdt);
		} else if (qdt instanceof DBUUID) {
			return new DBUUIDRenderer<A>(example, (DBUUID) qdt);
		} else if (qdt instanceof DBUnknownDatatype) {
			return new DBUnknownDatatypeRenderer<A>(example, (DBUnknownDatatype) qdt);
		} else if (qdt instanceof DBUntypedValue) {
			return new DBUntypedValueRenderer<A>(example, (DBUntypedValue) qdt);
		} // the following types need to be checked last as they have subtypes 
		else if (qdt instanceof DBDate) {
			return new DBDateRenderer<A>(example, (DBDate) qdt);
		} else if (qdt instanceof DBEnum) {
			return new DBEnumRenderer(example, (DBEnum) qdt);
		} else if (qdt instanceof DBLargeText) {
			return new DBLargeTextRenderer<A>(example, (DBLargeText) qdt);
		} else if (qdt instanceof DBLargeObject) {
			return new DBLargeObjectRenderer(example, (DBLargeObject) qdt);
		} else if (qdt instanceof DBNumber) {
			return new DBNumberRenderer<A>(example, (DBNumber) qdt);
		} else if (qdt instanceof DBString) {
			return new DBStringRenderer<A>(example, (DBString) qdt);
		} // and a default handler for all those ones I've forgotten
		else {
			return new DefaultDBRowPropertyRenderer(example, qdt);
		}
	}

}
