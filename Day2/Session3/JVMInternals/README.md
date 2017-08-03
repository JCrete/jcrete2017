#JVM internals

Related to a question in the #JVM internals Talk. Here some additional information regarding the perfomance of the AWS Lambda Java execution. It's extracted from the official documentation (where it's not that obvious to find).

from the FAQ (https://aws.amazon.com/lambda/faqs/)
>*Q: How are compute resources assigned to an AWS Lambda function?*
In the AWS Lambda resource model, you choose the amount of memory you want for your function, and are allocated proportional CPU power and other resources. For example, choosing 256MB of memory allocates approximately twice as much CPU power to your Lambda function as requesting 128MB of memory and half as much CPU power as choosing 512MB of memory. You can set your memory in 64MB increments from 128MB to 1.5GB.

This at least reveals selecting more memory means more CPU power and therefore shorter execution time.

The AWS Lambda has a monthly free tier (https://aws.amazon.com/lambda/pricing/)
>The Lambda free tier includes 1M free requests per month and 400,000 GB-seconds of compute time per month.

To know what it actually costs when this free `compute time per month` is over this calculator is really handy http://s3.amazonaws.com/lambda-tools/pricing-calculator.html (edited)