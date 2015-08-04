# Build Notes #

# Build Cycle 1 #
> I, Nathan, want to build a service using SensorManager that records data into (a) Content Provider array(s) to be accessed by the Filter (Mike). The Filter outputs usable information (e.g. velocity) into a different Content Provider. This information is used by MapClass (Kim) to move a dot on a screen. MapClass provides info in a third Content Provider containing dot location, map size, and absolute and relative wall locations. The information in the Filter and MapClass Content Providers will be used by CollisionManager (Roger) to avoid errors and correct/predict position.

Of course, this dot won't move in this first cycle.

Our phone seems to be the AT&T version of the Nexus S which has made it impervious to attempts to upgrade it. You can try.

Our phone is running Android 3.1.4.1.5.9.2.6.5

Kernel 2.6.35.7-g3cc95e3

API level 12 (not 13 like on 3.2)

Unlocked



## My job: ##
Build a service that records acellerometer data into a Content Provider

Use the ContentResolver class to input data into ContentProvider.


so my app looks like
Activity running xml
Activity does StartService() (accelerometerService)
Activity continually query() a ContentResolver for accel data
or maybe the service can update it automatically...

in AndroidManifest.xml declare my activity, service, content provider.

an intent starts my service
it will be a bound service, only needed when running a map.

The Content Provider that stores the values from the accelerometer might be most efficient as a circularly linked list to avoid shifting all of the data every update. You would only have to shift the start and end points every update.

we don't need a content provider. also the linked list was a silly idea. changing it to multiple float arrays inside of the service.