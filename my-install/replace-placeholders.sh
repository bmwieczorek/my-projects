#!/bin/bash
grep -rl mycompany * | xargs sed -i 's/mycompany/YOUR_COMPANY/g'
grep -rl myusername * | xargs sed -i 's/myusername/YOUR_USERNAME/g'

