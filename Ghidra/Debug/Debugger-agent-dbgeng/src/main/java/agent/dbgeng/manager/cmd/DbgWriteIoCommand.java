/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package agent.dbgeng.manager.cmd;

import java.nio.ByteBuffer;

import agent.dbgeng.manager.DbgThread;
import agent.dbgeng.manager.impl.DbgManagerImpl;

/**
 * Implementation of {@link DbgThread#writeMemory(long, ByteBuffer, int)}
 */
public class DbgWriteIoCommand extends AbstractDbgCommand<Void> {

	private final long addr;
	private final ByteBuffer buf;
	private final int interfaceType;
	private final int busNumber;
	private final int addressSpace;
	private final int len;

	public DbgWriteIoCommand(DbgManagerImpl manager, long addr, ByteBuffer buf, int len,
			int interfaceType, int busNumber, int addressSpace) {
		super(manager);
		this.addr = addr;
		this.interfaceType = interfaceType;
		this.busNumber = busNumber;
		this.addressSpace = addressSpace;
		this.buf = buf.duplicate();
		this.len = len;
	}

	@Override
	public void invoke() {
		manager.getDataSpaces()
				.writeIo(interfaceType, busNumber, addressSpace, addr, buf, buf.remaining());
	}

}
