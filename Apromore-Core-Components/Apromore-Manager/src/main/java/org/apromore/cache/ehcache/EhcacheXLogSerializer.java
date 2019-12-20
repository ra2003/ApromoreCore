package org.apromore.cache.ehcache;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferInputStream;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
//import de.javakaffee.kryoserializers.UUIDSerializer;
import org.deckfour.xes.extension.XExtension;
import org.deckfour.xes.extension.std.XLifecycleExtension;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.impl.*;
import org.ehcache.spi.serialization.Serializer;
import org.ehcache.spi.serialization.SerializerException;
import org.objenesis.strategy.StdInstantiatorStrategy;
import org.xerial.snappy.Snappy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;

public class EhcacheXLogSerializer implements Serializer<XLog> {

//    private final Serializer<XLog> serializer;

    protected static final Kryo kryo = new Kryo();

    public EhcacheXLogSerializer(ClassLoader classLoader) {

    }

    @Override
    public ByteBuffer serialize(XLog object) throws SerializerException {
        kryo.setWarnUnregisteredClasses(true);

        kryo.register(org.eclipse.collections.impl.set.mutable.UnifiedSet.class);
        kryo.register(UUID.class, new UUIDSerializer());
//        kryo.register(XAttributeMap.class);
        kryo.register(XAttributeMapImpl.class);
//        kryo.register(XAttributeMap.class, new XAttributeMapSerializer());
//        kryo.register(XAttributeMapImpl.class, new XAttributeMapSerializer());
//        kryo.register(XAttributeMapLazyImpl.class, new XAttributeMapSerializer());
        kryo.register(java.net.URI.class);
        kryo.register(java.util.Date.class);

        kryo.register(XLogImpl.class);
        kryo.register(XTraceImpl.class);
        kryo.register(XEventImpl.class);
        kryo.register(XAttributeContinuousImpl.class);
        kryo.register(XAttributeLiteralImpl.class);
        kryo.register(org.deckfour.xes.extension.std.XOrganizationalExtension.class);
        kryo.register(org.eclipse.collections.impl.set.mutable.UnifiedSet.class);
        kryo.register(XLifecycleExtension.class);
        kryo.register(org.deckfour.xes.id.XID.class);


        kryo.register(XLogImpl.class);
        kryo.register(XTraceImpl.class);
        kryo.register(XEventImpl.class);
        kryo.register(XAttributeBooleanImpl.class);
        kryo.register(XAttributeCollectionImpl.class);
        kryo.register(XAttributeContainerImpl.class);
        kryo.register(XAttributeDiscreteImpl.class);
        kryo.register(XAttributeIDImpl.class);
        kryo.register(XAttributeListImpl.class);
        kryo.register(XAttributeLiteralImpl.class);
        kryo.register(XAttributeTimestampImpl.class);
        kryo.register(XAttributeImpl.class);
        kryo.register(XsDateTimeFormat.class);
        kryo.register(XExtension.class);
        kryo.register(XLifecycleExtension.class);

        kryo.register(org.deckfour.xes.extension.std.XConceptExtension.class);
        kryo.register(org.deckfour.xes.extension.std.XTimeExtension.class);

        kryo.register(org.deckfour.xes.extension.std.XIdentityExtension.class);


        kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));

//        Output output = new Output( new ByteArrayOutputStream(), 1024 * 1024 * 1024 * 1);

        ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();
//        DeflaterOutputStream deflaterOutputStream =
//                new DeflaterOutputStream(byteArrayOutputStream);
        Output output = new Output(byteArrayOutputStream);

        kryo.writeObject(output, object);
        System.out.println("**************** Kryo serialisation size: " + output.toBytes().length);
//        output.flush();
        output.close();

        //compression off
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return ByteBuffer.wrap(bytes);

        // compression on
//        byte[] bytes = byteArrayOutputStream.toByteArray();
//        try {
//            byte[] compressBytes = Snappy.compress(bytes);
//            return ByteBuffer.wrap(compressBytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    @Override
    public XLog read(ByteBuffer byteBuffer) throws SerializerException, ClassNotFoundException {

        kryo.setWarnUnregisteredClasses(true);
        kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));

        // Register XLog model classes
        kryo.register(org.eclipse.collections.impl.set.mutable.UnifiedSet.class);
        kryo.register(UUID.class, new UUIDSerializer());
        kryo.register(XAttributeMapImpl.class);
        kryo.register(java.net.URI.class);
        kryo.register(java.util.Date.class);

        kryo.register(XLogImpl.class);
        kryo.register(XTraceImpl.class);
        kryo.register(XEventImpl.class);
        kryo.register(XAttributeContinuousImpl.class);
        kryo.register(XAttributeLiteralImpl.class);
        kryo.register(org.deckfour.xes.extension.std.XOrganizationalExtension.class);
        kryo.register(org.eclipse.collections.impl.set.mutable.UnifiedSet.class);
        kryo.register(XLifecycleExtension.class);
        kryo.register(org.deckfour.xes.id.XID.class);


        kryo.register(XLogImpl.class);
        kryo.register(XTraceImpl.class);
        kryo.register(XEventImpl.class);
        kryo.register(XAttributeBooleanImpl.class);
        kryo.register(XAttributeCollectionImpl.class);
        kryo.register(XAttributeContainerImpl.class);
        kryo.register(XAttributeDiscreteImpl.class);
        kryo.register(XAttributeIDImpl.class);
        kryo.register(XAttributeListImpl.class);
        kryo.register(XAttributeLiteralImpl.class);
        kryo.register(XAttributeTimestampImpl.class);
        kryo.register(XAttributeImpl.class);
        kryo.register(XsDateTimeFormat.class);
        kryo.register(XExtension.class);
        kryo.register(XLifecycleExtension.class);

        kryo.register(org.deckfour.xes.extension.std.XConceptExtension.class);
        kryo.register(org.deckfour.xes.extension.std.XTimeExtension.class);

        kryo.register(org.deckfour.xes.extension.std.XIdentityExtension.class);

        //compression off
        Input input =  new Input(new ByteBufferInputStream(byteBuffer)) ;
        return kryo.readObject(input, XLogImpl.class);

        // compression on
//        byte[] bytes = new byte[byteBuffer.remaining()];
//        byteBuffer.get(bytes);
//        try {
//            byte[] srcBytes = Snappy.uncompress(bytes);
//
//            Input input = new Input(new ByteBufferInputStream(ByteBuffer.wrap(srcBytes)));
//            return kryo.readObject(input, XLogImpl.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    @Override
    public boolean equals(final org.deckfour.xes.model.XLog object, final ByteBuffer binary) throws ClassNotFoundException, SerializerException {
        return object.equals(read(binary));
    }
//    public boolean equals(T object, ByteBuffer binary) throws SerializerException, ClassNotFoundException {
//        return serializer.equals(object, binary);
//    }

}
