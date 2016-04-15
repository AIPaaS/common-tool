package com.ai.paas.ipaas.serialize;

import java.io.IOException;

/**
 * Object output.
 * 
 * @author qian.lei
 */
public interface ObjectOutput extends DataOutput {

	/**
	 * write object.
	 * 
	 * @param obj
	 *            object.
	 */
	void writeObject(Object obj) throws IOException;

}