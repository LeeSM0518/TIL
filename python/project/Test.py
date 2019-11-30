import asyncio
from threading import Thread

import functools

# so print always flushes to stdout
print = functools.partial(print, flush=True)


def start_loop(loop):
    def run_forever(loop):
        print("Setting loop to run forever")
        asyncio.set_event_loop(loop)
        loop.run_forever()
        print("Leaving run forever")

    asyncio.set_event_loop(loop)
    print("Spawaning thread")
    thread = Thread(target=run_forever, args=(loop,))
    thread.start()


class Foo:
    def __init__(self, loop):
        print("in foo init")
        self.queue = asyncio.Queue()
        asyncio.run_coroutine_threadsafe(self.consumer(self.queue), loop)
        self.future = asyncio.run_coroutine_threadsafe(self.consumer(self.queue), loop)

    async def consumer(self, queue):
        print("In consumer")
        while True:
            message = await queue.get()
            print(f"Got message {message}")
            if message == "END OF QUEUE":
                print(f"exiting consumer")
                break
            print(f"Processing {message}...")

    def join(self):
        self.future.result()


def main():
    loop = asyncio.new_event_loop()
    start_loop(loop)
    f = Foo(loop)
    asyncio.run_coroutine_threadsafe(f.queue.put("this is a message"), loop)
    asyncio.run_coroutine_threadsafe(f.queue.put("END OF QUEUE"), loop)

    loop.call_soon_threadsafe(f.queue.put_nowait, 'this is a message')
    loop.call_soon_threadsafe(f.queue.put_nowait, 'END OF QUEUE')
    # wait for the stop to propagate and complete
    while loop.is_running():
        pass
    f.join()
    loop.call_soon_threadsafe(loop.stop)


if __name__ == "__main__":
    main()