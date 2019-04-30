from PIL import Image
import tensorflow as tf
import numpy as np
x_train = (100,1280,175)
y_train = (100,460)
# redonda,blanca,negra,pos0,pos1....redonda,blanca,negra,pos0,pos1
#100000000010000000

 
model = tf.keras.models.Sequential([
  tf.keras.layers.Flatten(input_shape=(1280, 175)),
  tf.keras.layers.Dense(1000, activation='relu'),
  tf.keras.layers.Dropout(0.2),
  tf.keras.layers.Dense(460, activation='softmax')
])

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])
im = Image.open('src/testImg4.jpg','r')
pix = im.load()
pixel = np.zeros((1,1280,175))

for i in range(1280):
  for j in range(175):
    pixel[0,i,j] = pix[i,j][0]/255.0
    #pix[i,j] = pix[j,i]

#model.fit(x_train, y_train, epochs=1)

#model.evaluate(x_test, y_test)

#pred = model.predict(pixel)
#print(pred.argmax())



