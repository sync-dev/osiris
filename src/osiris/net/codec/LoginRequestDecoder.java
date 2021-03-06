package osiris.net.codec;

/*
 * Osiris Emulator
 * Copyright (C) 2011  Garrett Woodard, Blake Beaupain, Travis Burtrum
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import osiris.io.Packet;
import osiris.io.PacketHeader;

/**
 * Decodes a login request.
 * 
 * @author Blake
 * 
 */
@ChannelPipelineCoverage("all")
public class LoginRequestDecoder extends FrameDecoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jboss.netty.handler.codec.frame.FrameDecoder#decode(org.jboss.netty
	 * .channel.ChannelHandlerContext, org.jboss.netty.channel.Channel,
	 * org.jboss.netty.buffer.ChannelBuffer)
	 */

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer in) throws Exception {
		in.readByte(); // Skip session type
		int length = in.readShort();
		if (in.readableBytes() < length) {
			return null;
		}
		ChannelBuffer buffer = ChannelBuffers.buffer(length);
		buffer.writeBytes(in);
		Packet loginPacket = new Packet(new PacketHeader(258, length), buffer);
		return loginPacket;
	}

}
