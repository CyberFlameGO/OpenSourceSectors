package pl.inder00.opensource.sectors.protocol.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.rsocket.Payload;
import io.rsocket.util.ByteBufPayload;
import pl.inder00.opensource.sectors.protobuf.ProtobufSectorSetup;
import pl.inder00.opensource.sectors.protocol.packet.EPacket;

public class ProtocolPayloadUtils {

    /**
     * Initial setup payload to establish connection with server ( password )
     *
     * @param password String / null
     * @return Initial payload
     */
    public static Payload createSetupPayload(String password) {
        return ByteBufPayload.create(ProtobufSectorSetup.SectorSetupPacket.newBuilder().setPassword(password != null ? password : "").build().toByteArray());
    }

    /**
     * Creating a request payload with specified data
     *
     * @param packet Request Packet
     * @param data   Data
     * @return Request Payload
     */
    public static Payload createRequestPayload(EPacket packet, byte[] data) {

        // create output buffer
        ByteBuf outputBuffer = Unpooled.buffer();
        outputBuffer.writeShort(packet.getPacketId());

        // return payload
        return data != null ? ByteBufPayload.create(data) : ByteBufPayload.create(outputBuffer);

    }

    /**
     * Creating a request payload
     *
     * @param packet Request Packet
     * @return Request Payload
     */
    public static Payload createRequestPayload(EPacket packet) {
        return createRequestPayload(packet, null);
    }

}
