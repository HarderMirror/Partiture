from PIL import Image
from time import time
import tensorflow as tf
from keras.layers import Dense, Activation
from keras.callbacks import TensorBoard
import numpy as np

width = 400
x_train = np.load("IA/x.npy",allow_pickle=True)
y_train = np.load("IA/y.npy",allow_pickle=True)
print(x_train)
x_test = np.zeros((1,width,175))
# redonda,blanca,negra,pos0,pos1....redonda,blanca,negra,pos0,pos1
#100000000010000000

 
model = tf.keras.models.Sequential([
  tf.keras.layers.Flatten(input_shape=(width, 175)),
  tf.keras.layers.Dense(30, activation='relu'),
  tf.keras.layers.Dropout(0.2),
  tf.keras.layers.Dense(23, activation='softmax')
])

model.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

tensorboard = TensorBoard(log_dir="logs/{}".format(time()))
im_test = Image.open("IA/images/5005.jpg")
pix = im_test.load()
for i in range(width):
  for j in range(175):
    x_test[0,i,j] = pix[i,j]/255.0
    #pix[i,j] = pix[j,i]

model.fit(x_train, y_train, epochs=5)

model.evaluate(x_train, y_train)

pred = model.predict(x_test)
#print(pred.argmax())
print(pred)




