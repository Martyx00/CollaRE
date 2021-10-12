from setuptools import setup, find_packages
from glob import glob

setup(
    name='collare',
    version='1.2',
    author='Martin Petran',
    license='Apache 2.0',
    description='Multi-tool collaboration for reverse engineers.',
    url='https://github.com/Martyx00/CollaRE',
    packages = ['collare'],
    package_data = {'collare' : ["icons/*"] },
    entry_points={
        'console_scripts': [
            'collare=collare.collare:main',
        ]
    },
    install_requires=[
        'PyQt5',
        'requests'
    ]
)

