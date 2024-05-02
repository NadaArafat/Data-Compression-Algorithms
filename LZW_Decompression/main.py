decompress = []
alreadyknown = []
alreadyknownno = []
maybeadded=""
n = int(input("Enter number of elements in array "))
seq = ""
for x in range(n):
    l = (input("enter elements{}".format(x+1)))
    decompress.append(int(l))
i = 128
ok=""
for y in range(0, n):
    if decompress[y] <= 122 and len(seq)==0:
        seq = seq + chr(decompress[y])
        maybeadded=chr(decompress[y])
    elif decompress[y] <= 122 and len(seq)!=0:
        if maybeadded+chr(decompress[y]) not in alreadyknown:
            alreadyknownno.append(i)
            i+=1
            alreadyknown.append(maybeadded+chr(decompress[y]))
            seq = seq + chr(decompress[y])
            ok=chr(decompress[y])
            maybeadded=chr(decompress[y])
    else:
         found=-1
         klma=""
         for x in range(0, len(alreadyknownno)):
             if decompress[y] ==alreadyknownno[x]:
                 found=x
                 klma=alreadyknown[x]
                 seq=seq+klma
                 ok=klma
                 for w in range(0,len(klma)):
                     if maybeadded + klma[w] not in alreadyknown:
                         alreadyknownno.append(i)
                         i+=1
                         alreadyknown.append(maybeadded+klma[w])
                         break
                     else:maybeadded+=klma[w]
                 break
         if found !=-1:
             maybeadded=klma
         else:
             maybeadded=ok
             maybeadded=maybeadded+maybeadded[0]
             alreadyknown.append(maybeadded)
             seq=seq+(maybeadded)
             alreadyknownno.append(i)
             ok=maybeadded
             i+=1
             maybeadded=alreadyknown[len(alreadyknown)-1]

print(seq)